/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { CUCOPComponentsPage, CUCOPDeleteDialog, CUCOPUpdatePage } from './cucop.page-object';
import * as path from 'path';

const expect = chai.expect;

describe('CUCOP e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let cUCOPUpdatePage: CUCOPUpdatePage;
    let cUCOPComponentsPage: CUCOPComponentsPage;
    let cUCOPDeleteDialog: CUCOPDeleteDialog;
    const fileNameToUpload = 'logo-jhipster.png';
    const fileToUpload = '../../../../../main/webapp/content/images/' + fileNameToUpload;
    const absolutePath = path.resolve(__dirname, fileToUpload);

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load CUCOPS', async () => {
        await navBarPage.goToEntity('cucop');
        cUCOPComponentsPage = new CUCOPComponentsPage();
        expect(await cUCOPComponentsPage.getTitle()).to.eq('comprasGobUpApp.cUCOP.home.title');
    });

    it('should load create CUCOP page', async () => {
        await cUCOPComponentsPage.clickOnCreateButton();
        cUCOPUpdatePage = new CUCOPUpdatePage();
        expect(await cUCOPUpdatePage.getPageTitle()).to.eq('comprasGobUpApp.cUCOP.home.createOrEditLabel');
        await cUCOPUpdatePage.cancel();
    });

    it('should create and save CUCOPS', async () => {
        const nbButtonsBeforeCreate = await cUCOPComponentsPage.countDeleteButtons();

        await cUCOPComponentsPage.clickOnCreateButton();
        await promise.all([
            cUCOPUpdatePage.setTipoInput('5'),
            cUCOPUpdatePage.setClaveCUCOPInput('5'),
            cUCOPUpdatePage.setPartidaEspInput('5'),
            cUCOPUpdatePage.setDescripcionInput('descripcion'),
            cUCOPUpdatePage.setNivelInput('5'),
            cUCOPUpdatePage.setCABMInput('cABM'),
            cUCOPUpdatePage.setUnidadMedInput('unidadMed'),
            cUCOPUpdatePage.setTipoContrataInput('tipoContrata'),
            cUCOPUpdatePage.setImagenInput(absolutePath)
        ]);
        expect(await cUCOPUpdatePage.getTipoInput()).to.eq('5');
        expect(await cUCOPUpdatePage.getClaveCUCOPInput()).to.eq('5');
        expect(await cUCOPUpdatePage.getPartidaEspInput()).to.eq('5');
        expect(await cUCOPUpdatePage.getDescripcionInput()).to.eq('descripcion');
        expect(await cUCOPUpdatePage.getNivelInput()).to.eq('5');
        expect(await cUCOPUpdatePage.getCABMInput()).to.eq('cABM');
        expect(await cUCOPUpdatePage.getUnidadMedInput()).to.eq('unidadMed');
        expect(await cUCOPUpdatePage.getTipoContrataInput()).to.eq('tipoContrata');
        expect(await cUCOPUpdatePage.getImagenInput()).to.endsWith(fileNameToUpload);
        await cUCOPUpdatePage.save();
        expect(await cUCOPUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await cUCOPComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last CUCOP', async () => {
        const nbButtonsBeforeDelete = await cUCOPComponentsPage.countDeleteButtons();
        await cUCOPComponentsPage.clickOnLastDeleteButton();

        cUCOPDeleteDialog = new CUCOPDeleteDialog();
        expect(await cUCOPDeleteDialog.getDialogTitle()).to.eq('comprasGobUpApp.cUCOP.delete.question');
        await cUCOPDeleteDialog.clickOnConfirmButton();

        expect(await cUCOPComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
