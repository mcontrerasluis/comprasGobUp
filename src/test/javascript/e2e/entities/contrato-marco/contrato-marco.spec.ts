/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ContratoMarcoComponentsPage, ContratoMarcoDeleteDialog, ContratoMarcoUpdatePage } from './contrato-marco.page-object';
import * as path from 'path';

const expect = chai.expect;

describe('ContratoMarco e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let contratoMarcoUpdatePage: ContratoMarcoUpdatePage;
    let contratoMarcoComponentsPage: ContratoMarcoComponentsPage;
    let contratoMarcoDeleteDialog: ContratoMarcoDeleteDialog;
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

    it('should load ContratoMarcos', async () => {
        await navBarPage.goToEntity('contrato-marco');
        contratoMarcoComponentsPage = new ContratoMarcoComponentsPage();
        expect(await contratoMarcoComponentsPage.getTitle()).to.eq('comprasGobUpApp.contratoMarco.home.title');
    });

    it('should load create ContratoMarco page', async () => {
        await contratoMarcoComponentsPage.clickOnCreateButton();
        contratoMarcoUpdatePage = new ContratoMarcoUpdatePage();
        expect(await contratoMarcoUpdatePage.getPageTitle()).to.eq('comprasGobUpApp.contratoMarco.home.createOrEditLabel');
        await contratoMarcoUpdatePage.cancel();
    });

    it('should create and save ContratoMarcos', async () => {
        const nbButtonsBeforeCreate = await contratoMarcoComponentsPage.countDeleteButtons();

        await contratoMarcoComponentsPage.clickOnCreateButton();
        await promise.all([
            contratoMarcoUpdatePage.setAutorInput('autor'),
            contratoMarcoUpdatePage.setTituloInput('titulo'),
            contratoMarcoUpdatePage.setDescripcionInput('descripcion'),
            contratoMarcoUpdatePage.setFechaVigenciaInput('2000-12-31'),
            contratoMarcoUpdatePage.setMontoInput('5'),
            contratoMarcoUpdatePage.setCantidadInput('5'),
            contratoMarcoUpdatePage.setImagenInput(absolutePath),
            contratoMarcoUpdatePage.setContratoInput(absolutePath),
            contratoMarcoUpdatePage.setAnexoInput(absolutePath),
            contratoMarcoUpdatePage.setConvenioInput(absolutePath),
            contratoMarcoUpdatePage.setRequisitosInput(absolutePath),
            // contratoMarcoUpdatePage.proveedorSelectLastOption(),
            contratoMarcoUpdatePage.cUCOPSelectLastOption(),
            contratoMarcoUpdatePage.dependenciaSelectLastOption()
        ]);
        expect(await contratoMarcoUpdatePage.getAutorInput()).to.eq('autor');
        expect(await contratoMarcoUpdatePage.getTituloInput()).to.eq('titulo');
        expect(await contratoMarcoUpdatePage.getDescripcionInput()).to.eq('descripcion');
        expect(await contratoMarcoUpdatePage.getFechaVigenciaInput()).to.eq('2000-12-31');
        expect(await contratoMarcoUpdatePage.getMontoInput()).to.eq('5');
        expect(await contratoMarcoUpdatePage.getCantidadInput()).to.eq('5');
        expect(await contratoMarcoUpdatePage.getImagenInput()).to.endsWith(fileNameToUpload);
        expect(await contratoMarcoUpdatePage.getContratoInput()).to.endsWith(fileNameToUpload);
        expect(await contratoMarcoUpdatePage.getAnexoInput()).to.endsWith(fileNameToUpload);
        expect(await contratoMarcoUpdatePage.getConvenioInput()).to.endsWith(fileNameToUpload);
        expect(await contratoMarcoUpdatePage.getRequisitosInput()).to.endsWith(fileNameToUpload);
        await contratoMarcoUpdatePage.save();
        expect(await contratoMarcoUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await contratoMarcoComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last ContratoMarco', async () => {
        const nbButtonsBeforeDelete = await contratoMarcoComponentsPage.countDeleteButtons();
        await contratoMarcoComponentsPage.clickOnLastDeleteButton();

        contratoMarcoDeleteDialog = new ContratoMarcoDeleteDialog();
        expect(await contratoMarcoDeleteDialog.getDialogTitle()).to.eq('comprasGobUpApp.contratoMarco.delete.question');
        await contratoMarcoDeleteDialog.clickOnConfirmButton();

        expect(await contratoMarcoComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
