/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ElementoOrdenComponentsPage, ElementoOrdenDeleteDialog, ElementoOrdenUpdatePage } from './elemento-orden.page-object';

const expect = chai.expect;

describe('ElementoOrden e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let elementoOrdenUpdatePage: ElementoOrdenUpdatePage;
    let elementoOrdenComponentsPage: ElementoOrdenComponentsPage;
    let elementoOrdenDeleteDialog: ElementoOrdenDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load ElementoOrdens', async () => {
        await navBarPage.goToEntity('elemento-orden');
        elementoOrdenComponentsPage = new ElementoOrdenComponentsPage();
        expect(await elementoOrdenComponentsPage.getTitle()).to.eq('comprasGobUpApp.elementoOrden.home.title');
    });

    it('should load create ElementoOrden page', async () => {
        await elementoOrdenComponentsPage.clickOnCreateButton();
        elementoOrdenUpdatePage = new ElementoOrdenUpdatePage();
        expect(await elementoOrdenUpdatePage.getPageTitle()).to.eq('comprasGobUpApp.elementoOrden.home.createOrEditLabel');
        await elementoOrdenUpdatePage.cancel();
    });

    it('should create and save ElementoOrdens', async () => {
        const nbButtonsBeforeCreate = await elementoOrdenComponentsPage.countDeleteButtons();

        await elementoOrdenComponentsPage.clickOnCreateButton();
        await promise.all([
            elementoOrdenUpdatePage.setCantidadInput('5'),
            elementoOrdenUpdatePage.setPrecioTotalInput('5'),
            elementoOrdenUpdatePage.estatusSelectLastOption(),
            elementoOrdenUpdatePage.setProveedorDInput('proveedorD'),
            elementoOrdenUpdatePage.setContratoMarcoDInput('contratoMarcoD'),
            elementoOrdenUpdatePage.contratoMarcoSelectLastOption(),
            elementoOrdenUpdatePage.proveedorSelectLastOption(),
            elementoOrdenUpdatePage.ordenCompraSelectLastOption()
        ]);
        expect(await elementoOrdenUpdatePage.getCantidadInput()).to.eq('5');
        expect(await elementoOrdenUpdatePage.getPrecioTotalInput()).to.eq('5');
        expect(await elementoOrdenUpdatePage.getProveedorDInput()).to.eq('proveedorD');
        expect(await elementoOrdenUpdatePage.getContratoMarcoDInput()).to.eq('contratoMarcoD');
        await elementoOrdenUpdatePage.save();
        expect(await elementoOrdenUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await elementoOrdenComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last ElementoOrden', async () => {
        const nbButtonsBeforeDelete = await elementoOrdenComponentsPage.countDeleteButtons();
        await elementoOrdenComponentsPage.clickOnLastDeleteButton();

        elementoOrdenDeleteDialog = new ElementoOrdenDeleteDialog();
        expect(await elementoOrdenDeleteDialog.getDialogTitle()).to.eq('comprasGobUpApp.elementoOrden.delete.question');
        await elementoOrdenDeleteDialog.clickOnConfirmButton();

        expect(await elementoOrdenComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
